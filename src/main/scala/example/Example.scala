package example

import minimal.router.{/, Integer, ObsRouter, Root}
import outwatch.dom._
import outwatch.dom.dsl._
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.raw.Window
import org.scalajs.dom.document.defaultView

import scala.concurrent.Future
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("example")
object Example {

  final val Pn = "#page"
  final val Inner1 = "#inner1"
  final val Inner2 = "#inner2"

  def page(n:Int) = Future (div (h4 ("Page "+n), a (href:=Pn+"/"+(n+1), "Click for page "+(n+1)),
    br(),a (href:=Inner1, "Click for inner no key "),
    br(),a (href:=Inner2, "Click for inner with key ")))

  val inner = """<h4>Page with innerHTML </h4>
               <a href ="#page/1"> Back to page 1</a>"""

  val pi1 = Future(div(prop("innerHTML"):=inner))

  val pi2 = Future(div(key:= "123", prop("innerHTML"):=inner))

  implicit val w:Window = org.scalajs.dom.document.defaultView

  val router = ObsRouter{path =>
    path match{
      case Root => page(1)
      case Root / Pn / Integer(p) => page(p)
      case Root / Inner1 => pi1
      case Root / Inner2 => pi2
      case _ => Future(div(h4("Not Found")))
    }
  }

@JSExport
  def main(args: Array[String]): Unit = {
    OutWatch.renderInto("#app", div(router)).unsafeRunSync()
  }
}

