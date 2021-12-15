package SingleCycleCore
import chisel3._
import chisel3.util._
import org.scalatest._
import chiseltest._

class TopModuleTest extends FreeSpec with ChiselScalatestTester{
    "Top Module Test" in {
        test(new TopModule()){c=>
        c.io.instruction.poke("h000000".U)
        c.io.PCin.poke(0.U)
        c.io.aluResult.expect(0.U)
        c.clock.step(2)
        }
    }
}