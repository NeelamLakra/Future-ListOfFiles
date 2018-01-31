import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val one = Future(3)
val two = Future(4)

val res:Future[Future[Int]]= one map{
  value =>
    two map(_*value)
  }

