package SingleCycleCore
import chisel3._
import chisel3.util._

class TypeDecode extends Module {
    val io = IO(new Bundle{
        val opcode = Input(UInt(7.W))
        val r = Output(Bool())
        val i = Output(Bool())
        val s = Output(Bool())
        val b = Output(Bool())
        val u = Output(Bool())
        val l = Output(Bool())
        val uj = Output(Bool())
        val auipc = Output(Bool())
        val jalr = Output(Bool())
    })
    io.r := 0.U
    io.i := 0.U
    io.s := 0.U
    io.b := 0.U
    io.u := 0.U
    io.l := 0.U
    io.uj := 0.U
    io.auipc := 0.U
    io.jalr := 0.U
    switch(io.opcode){
        is(51.U){
            io.r := 1.U
        }
        is(19.U){
            io.i := 1.U
        }
        is(35.U){
            io.s := 1.U
        }
        is(99.U){
            io.b := 1.U
        }
        is(55.U){
            io.u := 1.U
        }
        is(23.U){
            io.auipc := 1.U
        }
        is(3.U){
            io.l := 1.U
        }
        is(103.U){
            io.jalr := 1.U
        }
        is(111.U){
            io.uj := 1.U
        }
    }
}
