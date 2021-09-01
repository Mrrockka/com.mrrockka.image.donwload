package model;

import lombok.Data;

@Data
public class UrlRequest {
    private String siteUrl;
    private String downloadPath;

    public UrlRequest(String[] args) {
        this.siteUrl = args.length > 0 ? args[0] : "";
        this.downloadPath = args.length > 1 ? args[1] : "C:/Users/v.rokka/Pictures/donwloaded/";
    }
}
