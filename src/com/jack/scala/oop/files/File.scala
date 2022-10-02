package com.jack.scala.oop.files

import com.jack.scala.oop.filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, contents: String) extends DirEntry(parentPath, name) {
    override def getType: String = "File"
    def asFile: File = this
    override def asDirectory: Directory = throw new FileSystemException("file can not be converted to a directory")
}
object File {
    def empty(parentPath: String, name: String): File =
        new File(parentPath ,name ,"")
}
