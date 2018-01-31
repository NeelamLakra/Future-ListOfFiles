import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


val listOfFuture: List[Future[Int]] = List(Future(2 + 2), Future(20 + 3),Future(2 + 347))

val combindFuture: Future[List[Int]] = Future.sequence(listOfFuture)