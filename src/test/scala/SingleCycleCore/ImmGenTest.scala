package SingleCycleCore
import chisel3._
import org.scalatest._
import chiseltest._

class ImmGenTest extends FreeSpec with ChiselScalatestTester{
    "Immediate Extension test" in {
        test(new ImmGen()){c=>
        // c.io.ins.poke("h00500293".U) //I-type
        // c.io.ins.poke("h00200113".U) //L-type
        // c.io.ins.poke("h0050a123".U) //S-type
        // c.io.ins.poke("h00314263".U) //B-type
        // c.io.ins.poke("h009fb137".U) //U-type
        // c.io.ins.poke("h0040006f".U) //UJ-type Jal
        // c.io.ins.poke("h004002e7".U) //UJ-type jalr

        c.clock.step(100)
        // c.io.immediate_Se.expect(5.S) //I-type
        // c.io.immediate_Se.expect(2.S) //L-type
        // c.io.immediate_Se.expect(2.S) //S-type
        // c.io.immediate_Se.expect(4.S) //B-type
        // c.io.immediate_Se.expect(2555.S) //U-type
        // c.io.immediate_Se.expect(4.S) //UJ-type jal
        // c.io.immediate_Se.expect(4.S) //UJ-type jalr
        }
    }
}