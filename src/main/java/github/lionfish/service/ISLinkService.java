package github.lionfish.service;

public interface ISLinkService {

    String encode(String path,String timestamp,String expired);

    String lookupFullLink(String hash);
}
