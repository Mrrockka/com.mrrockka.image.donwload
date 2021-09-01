package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Matcher;

import static utils.Const.IMG_PATTERN;

public class Downloader {

    public static void download(Set<URL> urls, String path) throws IOException {
        Files.createDirectories(Paths.get(path));
        urls.forEach(img -> {
            try (ReadableByteChannel fileInput = Channels.newChannel(img.openStream())) {
                Matcher matcher = IMG_PATTERN.matcher(img.getPath());
                FileChannel fileChannel = new FileOutputStream(path + (matcher.find() ? matcher.group(1) : ".jpg")).getChannel();
                fileChannel.transferFrom(fileInput, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
