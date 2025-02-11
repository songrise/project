/**
 * -*- coding : utf-8 -*-
 *
 * @FileName : Disk.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-11-17
 * @Github ：https://github.com/songrise
 * @Descriptions: An encapsulation of root dir, working dir, and capacity.
 * Generally, it is just a delegate class for Directory. But we still believe
 * this encapsulation is necessary to provide some kind of separation .
 **/

package hk.edu.polyu.comp.comp2021.cvfs.model.fileSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Impletents Disk interface, encapsulates Directory and capacity.
 */
public class ConcreteDisk implements Serializable, Disk {
    private static final long serialVersionUID = 2021L;
    private final int capacity;
    private final Directory root;
    private Directory workingDir;

    /**
     * @param capacity size of Disk
     * @throws IllegalArgumentException if capacity<0
     */
    public ConcreteDisk(int capacity) throws IllegalArgumentException{
        if (capacity < 0) {
            throw new IllegalArgumentException("Disk size cannot < 0");
        } else {
            this.capacity = capacity;
        }
        this.root = Directory.createRoot();
        workingDir = root;
    }

    // -----------------Private methods----------------//

    private int calStorage() {
        int size = 0;
        for (File f : root.rList()) {
            size += f.getSize();
        }
        return size;
    }

    private boolean canStore(String contentToStore) {
        int size = this.calStorage();
        final int newFileSize = contentToStore.length() * 2 + 40;
        return newFileSize + size <= this.capacity;
    }


    // -----------------Public methods----------------//
    @Override
    public File findFile(String name) {
        return this.workingDir.findFile(name);
    }

    @Override
    public void makeDocument(String docName, String typeStr, String content) {
        if (this.canStore(content))
            workingDir.createDocument(docName, typeStr, content);
        else
            throw new IllegalStateException("Insufficient storage in current disk!");
    }

    @Override
    public ArrayList<File> list() {
        return workingDir.list();
    }

    @Override
    public ArrayList<File> rList() {
        return workingDir.rList();
    }

    @Override
    public void changeDir(String newDirName) {
        try {
            File f = this.workingDir.findFile(newDirName);
            if (f.isDirectory()) {
                workingDir = (Directory) f;
            } else {
                throw new IllegalArgumentException(newDirName + " is not name of a directory!");
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(newDirName + " not found in current directory!");
        }
    }

    @Override
    public Directory getWorkingDir() {
        return this.workingDir;
    }

    @Override
    public String getWorkingDirName() {
        return this.workingDir.getName();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void makeDir(String dirName) {
        workingDir.createDirectory(dirName);
    }

    @Override
    public void deleteFile(String fileName) {
        workingDir.deleteFile(fileName);
    }

    @Override
    public void rename(String oldName, String newName) {
        workingDir.renameFile(oldName, newName);
    }

    @Override
    public String toString() {
        return "Disk{" + "capacity=" + capacity + ", root=" + root + ", workingDir=" + workingDir + '}';
    }
}
