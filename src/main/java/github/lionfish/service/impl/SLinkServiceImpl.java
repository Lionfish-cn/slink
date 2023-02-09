package github.lionfish.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.hash.Hashing;
import github.lionfish.entity.SLink;
import github.lionfish.repository.SLinkRepository;
import github.lionfish.service.ISLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class SLinkServiceImpl implements ISLinkService {
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final String SHORT_LINK_PREFIX = "http://localhost:8080/a/";

    @Autowired
    SLinkRepository sLinkRepository;

    @Override
    public String encode(String link, String timestamp, String expired) {
        try {
            String tt = "#timestamp=" + timestamp;
            link = link + tt;
            long pad = Hashing.murmur3_32().hashUnencodedChars(link).padToLong();
            String encode = encode(pad);//生成了5位字符串

            String fullLink = link.replaceAll(tt, "");
            SLink sLink = SLink.builder().fullLink(fullLink).build();

            List<SLink> all = sLinkRepository.findAll(Example.of(sLink));
            if (all.isEmpty()) {
                sLink.setCreateTime(new Date());
                sLink.setShortLink(encode);
                sLink.setTimestamp(timestamp);
                sLink.setExpired(Integer.parseInt(expired));
                sLinkRepository.save(sLink);
            } else {
                sLink = all.get(0);
            }

            return JSON.toJSONString(sLink);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String lookupFullLink(String hash) {
        SLink sLink = SLink.builder().shortLink(hash).build();
        Example<SLink> example = Example.of(sLink);
        List<SLink> sLinks = sLinkRepository.findAll(example);
        sLink = sLinks.get(0);
        return sLink.getFullLink();
    }

    private String encode(long pad) {
        BigInteger of = BigInteger.valueOf(pad);
        StringBuilder value = new StringBuilder();
        while (!of.equals(BigInteger.ZERO)) {
            BigInteger[] bigIntegers = of.divideAndRemainder(BigInteger.valueOf(64L));
            value.append(ALPHABETS.charAt(bigIntegers[1].intValue()));
            of = bigIntegers[0];
        }
        return value.reverse().toString();
    }
}
