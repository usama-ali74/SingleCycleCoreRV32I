package SingleCycleCore
import chisel3._
import chisel3.util._


class alu extends Module{
    val io = IO(new Bundle{
    val A = Input(UInt(32.W))
    val B = Input(UInt(32.W))
    val selec = Input(UInt(5.W))
    val output = Output(UInt(32.W))
    val branch = Output(Bool())
    val ins = Input(UInt(32.W))
    })

    io.output := 0.U
    switch(io.selec){
        is (0.U){
            io.output :=  io.A + Mux( io.ins (30) , -io.B , io.B )  //Add //Sub
        }

    //sll
    is (1.U){
        io.output := io.A << io.B(4,0)
    }

    //SLT
    is (2.U){
        when(io.A < io.B){
            io.output := 1.U
        }.otherwise{
            io.output := 0.U
        }    
    }

    //SLTU
    // is (3.U){
    //     when(io.A < io.B){
    //         io.output:= 1.asSInt
    //     }.otherwise{
    //         io.output:=0.asSInt
    //     }
    // }

    //xor
    is(4.U){
        io.output:=io.A^io.B
    }

    //slr
    is(5.U){
        io.output:=io.A >> io.B(4,0)
    }

    //or
    is(6.U){
        io.output:=io.A | io.B
    }

    //AND
    is(7.U){
        io.output:=io.A & io.B
    }

    //SRA
    // is(13.U){
    //     when (io.A(31)===1.U){
	// 	io.output := (io.A(4,0) >> io.B(4,0)).asSInt
    //     }
    // }

    //BEQ
    is(16.U){
        when(io.A === io.B){
            io.output:= 1.U
        }.otherwise{
            io.output:=0.U
        }
    }

    //bne
    is(17.U){
        when(io.A =/= io.B){
            io.output:= 1.U
        }.otherwise{
            io.output:=0.U
        }
    }

    //bge
    is(21.U){
        when(io.A >= io.B){
            io.output:= 1.U
        }.otherwise{
            io.output:=0.U
        }
    }

    //blt
    is(11.U){
        when(io.A < io.B){
            io.output:= 1.U
        }.otherwise{
            io.output:=0.U
        }
    }
}

    
    when (io.output === 1.U & io.selec(4,3) === "b10".U){
        io.branch := 1.U
    }.otherwise{
        io.branch := 0.U
    }

        }

