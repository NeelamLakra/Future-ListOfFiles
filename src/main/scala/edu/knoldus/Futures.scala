package edu.knoldus

import java.io.File
import java.nio.file.Files

import org.apache.log4j.Logger

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


class Futures {

  def search(filePath: String, resultList: List[File]): List[File] = {
    val directoryList = List(new File(filePath))
    getListOfFiles(resultList, directoryList)
  }

  def getListOfFiles(resultList: List[File], directoryList: List[File]): List[File] = {
    directoryList match {
      case head :: tail => getListOfFiles(resultList ::: head.listFiles.filter(_.isFile).toList, tail ::: head.listFiles.filter(_.isDirectory).toList)
      case _ => resultList
    }
  }
}

object Application extends App {
  val log = Logger.getLogger(this.getClass)
  val futureObj = new Futures
  val resultList = List()
  val result: Future[List[File]] = Future {
    futureObj.search("src/main/Folder1", resultList)
  }

  result onComplete {
    case Success(value) => log.info("list" + value)
    case Failure(exception) => log.info(("Error" + exception.getMessage))
  }
  val time = 300
  Thread.sleep(time)

}

