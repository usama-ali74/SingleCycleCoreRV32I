package SingleCycleCore
import chisel3._
import org.scalatest._
import chiseltest._

class DataMemoryTest extends FreeSpec with ChiselScalatestTester{
    "data memory test" in {
        test(new DataMemory()){c=>
        c.io.DataAddr.poke(0.U)
        c.io.DataIn.poke(15.S)
        c.io.store.poke(1.U)
        c.io.load.poke(1.U)
        c.clock.step(50)
        c.io.DataOut.expect(15.S)
        }
    }
}