import java.util.HashMap;

public class FileSignature {
    private static FileSignature single_instance = null;
    private HashMap<String, FileType> fileSignatures = new HashMap<String, FileType>();

    private FileSignature() {
        intializeFileSignature();
    }

    public static FileSignature getFileSignature() {
        if(single_instance == null){
            single_instance = new FileSignature();
        }
        return single_instance;
    }

    public FileType getFileType(String signature) {
        return fileSignatures.get(signature);
    }

    private void intializeFileSignature() {
        fileSignatures.put("89504e47", FileType.IMAGE_PNG);
        fileSignatures.put("3c3f786d", FileType.IMAGE_SVG);
        fileSignatures.put("ffd8ffdb", FileType.IMAGE_JPG);
        fileSignatures.put("ffd8ffee", FileType.IMAGE_JPG);
        fileSignatures.put("ffd8ffe1", FileType.IMAGE_JPG);
        fileSignatures.put("ffd8ffe0", FileType.IMAGE_JPG);
        fileSignatures.put("49492a00", FileType.IMAGE_TIFF);
        fileSignatures.put("38425053", FileType.IMAGE_PSD);
        fileSignatures.put("25504446", FileType.IMAGE_PDF);
        fileSignatures.put("47494638", FileType.IMAGE_GIF);
        fileSignatures.put("00000018", FileType.VIDEO_MP4);
        fileSignatures.put("3026b275", FileType.VIDEO_WMV);
        fileSignatures.put("52494646", FileType.VIDEO_AUDIO_AVI_WAV);
        fileSignatures.put("41564950", FileType.VIDEO_AVI);
        fileSignatures.put("1a45dfa3", FileType.VIDEO_MKV);
        fileSignatures.put("464c56  ", FileType.VIDEO_FLV);
        fileSignatures.put("664c6143", FileType.AUDIO_FLAC);
        fileSignatures.put("57415645", FileType.AUDIO_WAV);
        fileSignatures.put("504b0304", FileType.COMPRESSED_ZIP);
        fileSignatures.put("377abcaf", FileType.COMPRESSED_7Z);
        fileSignatures.put("52617221", FileType.COMPRESSED_RAR);
        fileSignatures.put("43443030", FileType.COMPRESSED_ISO);
        fileSignatures.put("cafebabe", FileType.JAVA_CLASS);
        fileSignatures.put("4d5a9000", FileType.EXE);
        fileSignatures.put("7f454c46", FileType.ELF);
    }
}