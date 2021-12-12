package SingleCycleCore
import chisel3._
import org.scalatest._
import chiseltest._

class AluControlTest extends FreeSpec with ChiselScalatestTester{
    "Alu Control Test" in {
        test(new AluControl()){c=>
        c.io.fun3.poke(1.U)
        c.io.fun7.poke(0.U)
        c.io.branch.poke(0.B)
        c.clock.step(50)
        c.io.aluOpp.expect(1.U)
        }
    }
}

