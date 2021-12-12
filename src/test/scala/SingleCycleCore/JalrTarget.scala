package SingleCycleCore
import chisel3._
import org.scalatest._
import chiseltest._

class JalrTargetTest extends FreeSpec with ChiselScalatestTester{
    "Jalr Target Test" in {
        test(new JalrTarget()){c=>
        c.io.a.poke(50.S)
        c.io.b.poke(50.S)
        c.io.output.expect(100.S)
        c.clock.step(100)
        }
    }
}