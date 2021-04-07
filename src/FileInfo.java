import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileInfo extends File {
    private String extension;
    private FileType filetype;

    FileInfo(String file_path) {
        super(file_path);
        this.filetype = extractFileType();
        this.extension = extractExtension();
    }

    public String getExtension() {
        return this.extension;
    }

    private String extractExtension() {
        String ext = "";
        switch (this.filetype) {
            case IMAGE_PNG:
                ext = ".png";
                break;
            case IMAGE_SVG:
                ext = ".svg";
                break;
            case IMAGE_GIF: 
                ext = ".gif";
                break;
            case IMAGE_JPG:
                ext = ".jpg";
                break;
            case IMAGE_PDF:
                ext = ".pdf";
                break;
            case IMAGE_PSD:
                ext = ".psd";
                break;
            case IMAGE_TIFF:
                ext = ".tiff";
                break;
            case AUDIO_FLAC:
                ext = ".flac";
                break;
            case AUDIO_MP3:
                ext = ".mp3";
                break;
            case AUDIO_WAV:
                ext = ".wav";
                break;
            case VIDEO_AVI:
                ext = ".avi";
                break;
            case VIDEO_FLV:
                ext = ".flv";
                break;
            case VIDEO_MKV:
                ext = ".mkv";
                break;
            case VIDEO_MP4:
                ext = ".mp4";
                break;
            case VIDEO_WMV:
                ext = ".wmv";
                break;
            case COMPRESSED_7Z:
                ext = ".7z";
                break;
            case COMPRESSED_ISO:
                ext = ".iso";
                break;
            case COMPRESSED_RAR:
                ext = ".rar";
                break;
            case COMPRESSED_ZIP:
                String file_names[] = this.getName().split("\\.");
                String file_ext = file_names[file_names.length-1];
                if (file_ext.contains("doc")){
                    ext = "." + file_ext;
                }else{
                    ext = ".zip";
                }
                break;
            case JAVA_CLASS:
                ext = ".class";
                break;
            case EXE:
                ext = ".exe";
                break;
            case ELF:
                ext = ".elf";
                break;
            default:
                String f_names[] = this.getName().split("\\.");
                if (f_names.length > 0) {
                    String f_ext = f_names[f_names.length-1];
                    System.out.println("Completly other file.");
                    ext = "." + f_ext;
                }else
                ext = ".other";
                break;
        }
        return ext;
    }

    private String getMagicBytes(int skip) {
        String magicBytes = "";
        try {
            InputStream inputStream = new FileInputStream(this.getAbsolutePath());
            inputStream.skip(skip);
            for (int i = 0; i < 4; i++) {
                int Byte = inputStream.read();
                String hex = Integer.toHexString(Byte);
                if (hex.length() == 1) magicBytes = magicBytes.concat("0"+hex);
                else magicBytes = magicBytes.concat(hex);
            }
            inputStream.close();

        } catch (Exception e) {
            System.out.println("FILEINPUT STREAM ERRORS\n\n\n\n\n");
            e.printStackTrace();
        }

        return magicBytes;
    }

    private FileType extractFileType() {
        FileSignature fileSignature = FileSignature.getFileSignature();
        String signature = getMagicBytes(0);
        System.out.println("file signature = " + signature);
        
        // for VIDEO_FLV type.
        if (signature.startsWith("464c56")) {
            signature = "464c56  ";

        }

        FileType type = fileSignature.getFileType(signature);

        if (type == FileType.VIDEO_AUDIO_AVI_WAV) {
            signature = getMagicBytes(8);
            type = fileSignature.getFileType(signature);
        }

        if (type == null){
            type = FileType.OTHER_FILETYPE;
        }

        return type;
    }
}
