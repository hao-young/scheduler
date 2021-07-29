package cn.ac.yhao.utils;

import com.drew.imaging.FileType;
import com.drew.imaging.FileTypeDetector;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.mp3.Mp3MetadataReader;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * @description:
 * @author: Daniel Young
 * @create: 2021-07-27 17:21
 */
public class ImageUtils {

    public static void main(String[] args) {
        File jpegFile = new File("/Users/daniel/Downloads/WechatIMG53.jpeg");

        GpsDirectory gpsDirectory = getGpsDirectory(jpegFile);
        if (Objects.nonNull(gpsDirectory)) {
            GeoLocation geoLocation = gpsDirectory.getGeoLocation();
            System.out.println("经度："+geoLocation.getLongitude());
            System.out.println("纬度："+geoLocation.getLatitude());
        }
    }

    public static GpsDirectory getGpsDirectory(File file){
        Metadata metadata = getMetadata(file);
/*
        Iterable<Directory>directories = metadata.getDirectories();
        StringBuffer sb = new StringBuffer();
        for (Directory directory : directories) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                if (tagName.equals("Image Height")) {
                    sb.append("Image Height : ").append(desc);
                } else if (tagName.equals("Image Width")) {
                    sb.append("\nImage Width : ").append(desc);
                } else if (tagName.equals("Date/Time Original")) {
                    sb.append("\nDate/Time:").append(desc);
                }else if (tagName.equals("GPS Latitude")) {
                    sb.append("\nLatitude : ").append(pointToLatlong(desc)).append("   原始：").append(desc);
                    System.err.println("纬度 : "+desc);
//                	System.err.println("纬度(度分秒格式) : "+pointToLatlong(desc));
                } else if (tagName.equals("GPS Longitude")) {
                    sb.append("\nLongitude : ").append(pointToLatlong(desc)).append("   原始：").append(desc);
                    System.err.println("经度: "+desc);
//                	System.err.println("经度(度分秒格式): "+pointToLatlong(desc));
                }
            }
        }
        System.out.println(sb.toString());*/

        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        return gpsDirectory;
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong (String point ) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60 ;
        return duStr.toString();
    }

    public static Metadata getMetadata(File file) {
        Metadata metadata = null;
        try (InputStream inputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            FileType fileType = FileTypeDetector.detectFileType(bufferedInputStream);
            switch (fileType) {
                case Png:
                    metadata = PngMetadataReader.readMetadata(file);
                    break;
                case Jpeg:
                    metadata = JpegMetadataReader.readMetadata(file);
                    break;
                case Mp4:
                    metadata = Mp4MetadataReader.readMetadata(file);
                    break;
                case Gif:
                    metadata = GifMetadataReader.readMetadata(file);
                    break;
                case Mp3:
                    metadata = Mp3MetadataReader.readMetadata(file);
                    break;
                default:
                    return metadata;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return metadata;
    }


}
