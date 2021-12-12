package SingleCycleCore
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._

class PcTest extends FreeSpec with ChiselScalatestTester{
    "program counter test" in {
        test(new Pc()){c=>
        c.io.input.poke(0.U)   
        c.clock.step(100) 
        c.io.pc.expect(0.U)    
        c.io.pc4.expect(4.U)    
        }
    }
}