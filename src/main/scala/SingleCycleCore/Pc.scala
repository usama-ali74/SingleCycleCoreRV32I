package SingleCycleCore
import chisel3._
import chisel3.util._

class Pc extends Module{
    val io = IO(new Bundle{
        val input = Input(UInt(32.W))
        val pc = Output(UInt(32.W))
    })

    val reg = RegNext(0.U(32.W))
    reg := io.input
    reg := reg + 4.U
    io.pc := reg
}