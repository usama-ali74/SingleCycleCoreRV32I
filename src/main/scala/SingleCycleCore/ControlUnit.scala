package SingleCycleCore
import chisel3._
import chisel3.util._

class ControlUnit extends Module{
    val io = IO(new Bundle{
        val Instruction = Input(UInt(32.W))
        val branch = Output(Bool())
        val RegWrite = Output(Bool())
        val Immediate = Output(Bool())
        val Load = Output(Bool())
        val Store = Output(Bool())
        val Utype = Output(Bool())
        val auipc = Output(Bool())
        val jlr = Output(Bool())
        val jal = Output(Bool())     
    })

    val decod = Module(new TypeDecode())
    decod.io.opcode := io.Instruction(6,0)
        io.branch:=0.B
        io.RegWrite:=0.B
        io.Immediate:=0.B
        io.Load:=0.B
        io.Store:=0.B
        io.Utype:=0.B
        io.auipc:=0.B
        io.jlr:=0.B
        io.jal:=0.B
    when(decod.io.i===1.U){
        io.RegWrite := 1.U
        io.Immediate := 1.U
    }.elsewhen(decod.io.r===1.U){
        io.RegWrite := 1.U
    }.elsewhen(decod.io.b===1.U){
        io.branch := 1.U
    }.elsewhen(decod.io.l===1.U){
        io.RegWrite := 1.U
        io.Immediate := 1.U
        io.Load := 1.U
    }.elsewhen(decod.io.s===1.U){
        io.Immediate := 1.U
        io.Store := 1.U
    }.elsewhen(decod.io.u===1.U){
        io.Utype := 1.U
    }.elsewhen(decod.io.auipc===1.U){
        io.RegWrite := 1.U
        io.auipc := 1.U
    }.elsewhen(decod.io.uj===1.U){
        io.jal := 1.U
        io.RegWrite := 1.U
    }.elsewhen(decod.io.jalr===1.U){
        io.jlr := 1.U
    }
    
}