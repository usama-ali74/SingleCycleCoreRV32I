package SingleCycleCore
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile


class TopModule extends Module {
    val io = IO(new Bundle{
        val instruction = Input(UInt(32.W))
        val aluResult = Output(UInt(32.W))
        // val PCin = Input(UInt(32.W))

    })

    val PC = Module(new Pc())
    val Insmem = Module(new InsMemory())
    val RegF = Module(new RegFile())
    val aluCntrl = Module(new AluControl())
    val Datameme = Module(new DataMemory())
    val alu = Module(new alu())
    val Immgen = Module(new ImmGen())
    val typdecode = Module(new TypeDecode())
    val CU = Module(new ControlUnit())
    // val jlrtarget = Module(new JalrTarget())

    typdecode.io.opcode := io.instruction(6,0)
    //jal
    when (Insmem.io.instOut(6,0) === 111.U){
        PC.io.input := (Immgen.io.immediate_Se).asUInt
    }
    //branch
    .elsewhen(Insmem.io.instOut(6,0) === 99.U){
        PC.io.input := (Immgen.io.immediate_Se).asUInt
    }
    //jalr
    .elsewhen(Insmem.io.instOut(6,0) === 103.U){
        PC.io.input := (Immgen.io.immediate_Se).asUInt
    }
    .otherwise{
        PC.io.input := 0.U
    }
    Insmem.io.instAddr := PC.io.pc

    //RegisterFile
    RegF.io.rs1 := Insmem.io.instOut(19,15)
    RegF.io.rs2 := Insmem.io.instOut(24,20)
    RegF.io.rd := Insmem.io.instOut(11,7)
    RegF.io.writeEnable := CU.io.RegWrite
    
    //ImmediateGenerate
    Immgen.io.ins := Insmem.io.instOut
    Immgen.io.PC := PC.io.pc

    // ALU CONtrol
    alu.io.selec := aluCntrl.io.aluOpp
    aluCntrl.io.fun3 := Insmem.io.instOut(14,12)
    aluCntrl.io.fun7 := Insmem.io.instOut(30)
    aluCntrl.io.branch :=  CU.io.branch

    //CU
    CU.io.Instruction := io.instruction

    //ALU
    alu.io.ins := io.instruction
    alu.io.A := Mux(CU.io.auipc,(PC.io.pc),(RegF.io.Aout).asUInt)

    val tmp = ((CU.io.Immediate) | (CU.io.Utype) | (CU.io.Store))
    alu.io.B := Mux(tmp,(Immgen.io.immediate_Se).asUInt,(RegF.io.Bout).asUInt)

    alu.io.selec := Mux(((CU.io.Store) |(CU.io.Load)),0.U,aluCntrl.io.aluOpp)
    RegF.io.writeBack := (alu.io.output).asSInt
    io.aluResult := alu.io.output

    //DataMem
    Datameme.io.DataAddr := alu.io.output(7,0)
    Datameme.io.DataIn := RegF.io.Bout
    Datameme.io.load := CU.io.Load
    Datameme.io.store := CU.io.Store
    RegF.io.writeBack :=  Datameme.io.DataOut

}
