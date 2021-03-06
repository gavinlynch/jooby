package org.jooby;

import org.jooby.handlers.AssetHandler;
import org.jooby.test.ServerFeature;
import org.junit.Test;

public class AssetHandlerFeature extends ServerFeature {

  {
    assets("/assets/**", new AssetHandler("/", getClass()));
  }

  @Test
  public void jsAsset() throws Exception {
    request()
        .get("/assets/file.js")
        .expect(200)
        .header("Content-Type", "application/javascript;charset=UTF-8")
        .header("Content-Length", 15)
        .header("Last-Modified", lastModified -> {
          request()
              .get("/assets/file.js")
              .header("If-Modified-Since", lastModified)
              .expect(304)
              .empty();
        });
  }

  @Test
  public void cssAsset() throws Exception {
    request()
        .get("/assets/file.css")
        .expect(200)
        .header("Content-Type", "text/css;charset=UTF-8")
        .header("Content-Length", 22)
        .header("Last-Modified", lastModified -> {
          request()
              .get("/assets/file.css")
              .header("If-Modified-Since", lastModified)
              .expect(304)
              .empty();
        });
  }

  @Test
  public void imageAsset() throws Exception {
    request()
        .get("/assets/favicon.ico")
        .expect(200)
        .header("Content-Type", "image/x-icon")
        .header("Content-Length", 2238)
        .header("Last-Modified", lastModified -> {
          request()
              .get("/assets/favicon.ico")
              .header("If-Modified-Since", lastModified)
              .expect(304)
              .empty();
        });
  }

  @Test
  public void emptyFile() throws Exception {
    request()
        .get("/assets/empty.css")
        .expect(200)
        .header("Content-Type", "text/css;charset=UTF-8")
        .header("Content-Length", 0)
        .header("Last-Modified", lastModified -> {
          request()
              .get("/assets/empty.css")
              .header("If-Modified-Since", lastModified)
              .expect(304)
              .empty();
        });
  }

  @Test
  public void largeFile() throws Exception {
    request()
        .get("/assets/jquery-2.1.4.js")
        .expect(200)
        .header("Content-Type", "application/javascript;charset=UTF-8")
        .header("Content-Length", 247597)
        .header("Last-Modified", lastModified -> {
          request()
              .get("/assets/file.js")
              .header("If-Modified-Since", lastModified)
              .expect(304)
              .empty();
        });
  }

}
