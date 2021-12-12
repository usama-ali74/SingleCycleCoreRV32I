package SingleCycleCore
import chisel3._
import chisel3.util._

class AluControl extends Module{
    val io = IO(new Bundle{
        val fun3 = Input(UInt(3.W))
        val fun7 = Input(UInt(1.W))
        val branch = Input(Bool())
        val aluOpp = Output(UInt(5.W))
    })
    io.aluOpp := 0.U
    val out1 = Cat(0.U,io.fun7,io.fun3)
    val out2 = Cat(2.U,io.fun3)
    when(io.branch){
        io.aluOpp := out2
    }.otherwise{
        io.aluOpp := out1
    }
}
