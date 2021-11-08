package Tools;

import java.io.FileInputStream;
import java.io.Serializable;

public class FileData implements Serializable {

	public FileInputStream file;

	public String extension;

	FileData(FileInputStream _file, String _extension) {
		this.file = _file;
		this.extension = _extension;
	}
}
