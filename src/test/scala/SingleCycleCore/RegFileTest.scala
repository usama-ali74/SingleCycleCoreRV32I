package SingleCycleCore
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._

class RegFileTest extends FreeSpec with ChiselScalatestTester{
    "Register File test" in {
        test(new RegFile()){c=>
        c.io.rs1.poke(0.U)    
        c.io.rs2.poke(10.U)    
        c.io.rd.poke(10.U)    
        c.io.writeBack.poke(10.S)    
        c.io.writeEnable.poke(1.U)    
        c.clock.step(100) 
        c.io.Aout.expect(0.S)    
        c.io.Bout.expect(10.S)    
        }
    }
}