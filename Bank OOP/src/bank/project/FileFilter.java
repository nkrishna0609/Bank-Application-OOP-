package bank.project;

import java.io.File;

public interface FileFilter {
    public abstract boolean accept(File file);
}
