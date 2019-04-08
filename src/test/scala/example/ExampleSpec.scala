package example

import cats.effect.IO
import monix.execution.Ack.Continue
import monix.execution.ExecutionModel.SynchronousExecution
import monix.execution.schedulers.TrampolineScheduler
import monix.execution.{Ack, Scheduler}
import org.scalatest._
import org.scalajs.dom._
import org.scalatest.concurrent.ScalaFutures
import outwatch.dom._
import outwatch.dom.dsl._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
//import monix.execution.Scheduler.Implicits.global

import scala.scalajs.concurrent.JSExecutionContext


class ExampleSpec extends AsyncFlatSpec with Matchers with ScalaFutures{
  //val scheduler =  TrampolineScheduler(Scheduler.global, SynchronousExecution)
  implicit def ioAssertionToFutureAssertion(io: IO[Assertion]): Future[Assertion] = io.unsafeToFuture()
  override implicit val executionContext = TrampolineScheduler(Scheduler.global, SynchronousExecution)

  "Future test1" should "work" in{
    Future{ 1 shouldEqual 1}(executionContext)
  }

  "Future test2" should "work" in{
    Future{ 1 shouldEqual 0}(executionContext)
  }

//  "Seat" should "resolve mass" in {
//    val s = Seat("1A", 129)
//    val x= for {
//      initial <- s.mass.last.runAsyncGetLast
//      _ = initial shouldEqual 0.0
//      Ack.Continue <- s.massHolder.onNext("190")
//      afterwards <- s.mass.last.runAsyncGetLast
//      _ = afterwards shouldEqual 191.0
//    } yield succeed
//
//whenReady(x){r=> r shouldEqual(succeed)}
//  }


//
//  "Seat" should "resolve mass" in {
//    val s = Seat("1A", 129)
//    for {
//      initial <- s.mass.lastL.runToFuture(executionContext)
//      _ = initial shouldEqual 1.0
//      Ack.Continue <- s.massHolder.onNext("190")
//      afterwards <- s.mass.lastL.runToFuture(executionContext)
//      _ = afterwards shouldEqual 191.0
//    } yield succeed
//  }




  //    var m=0.0
//
  "Basic Seat mass edit test" should "evaluate correctly" in {
    val s = Seat("1A", 129)
    s.mass.head.map(_ shouldEqual 0d).lastL.runToFuture

    s.massHolder.onNext("") shouldEqual Continue
    s.mass.head.map(_ shouldEqual 0d).lastL.runToFuture

    s.massHolder.onNext("170") shouldEqual Continue
    s.massHolder.onNext("190") shouldEqual Continue
    s.mass.head.map(_ shouldEqual 190d).lastL.runToFuture
  }
//
//  "Seat" should "calculate corrent moment" in {
//    var mo=0.0
//    val s= Seat("1A",129)
//    s.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 0.0
//    s.massHolder.onNext("190")
//    s.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 190.0 * 129.0
//  }
//
//  "Row" should "calculate corrent mass and moment" in {
//    var m,a,mo=0.0
//
//    val s= Seat("1A",129)
//    val r=Row("Test Row", Seq(s))
//    r.mass.last.foreach(x=> m=x)
//    m shouldEqual 0.0
//    r.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 0.0
//    r.arm.last.foreach(x=> a=x)
//    a shouldEqual 0.0
//    s.massHolder.onNext("190")
//    r.mass.last.foreach(x=> m=x)
//    m shouldEqual 190.0
//    r.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 190.0 *129.0
//    r.arm.last.foreach(x=> a=x)
//    a shouldEqual 129.0
//  }
//
//  "Row" should "calculate correct mass and moment" in {
//    var m,a,mo=0.0
//    val s1= Seat("1A",129)
//    val s2= Seat("1C",129)
//    val r=Row("Test Row", Seq(s1,s2))
//    r.mass.last.foreach(x=> m=x)
//    m shouldEqual 0.0
//    r.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 0.0
//    r.arm.last.foreach(x=> a=x)
//    a shouldEqual 0.0
//
//    s1.massHolder.onNext("190")
//    r.mass.last.foreach(x=> m=x)
//    m shouldEqual 190.0
//    r.moment.last.foreach(x=> mo=x)
//    mo shouldEqual 190.0 *129.0
//    r.arm.last.foreach(x=> a=x)
//    a shouldEqual 129.0
//
//    s2.massHolder.onNext("170")
//    r.mass.last.foreach(x=> m=x)
//    m shouldEqual 190.0+170
//    r.moment.last.foreach(x=> mo=x)
//    mo shouldEqual (190.0+170.0)*129
//    r.arm.last.foreach(x=> a=x)
//    a shouldEqual 129.0
//    var txt =""
//    r.text.foreach(t=> txt=t)
//    txt shouldEqual "Test Row"

}
