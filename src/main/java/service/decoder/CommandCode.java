package service.decoder;

public enum CommandCode {

    // BYTE-ORIENTED FILE REGISTER OPERATIONS
    ADDWF,
    ANDWF,
    CLRF,
    CLRW,
    COMF,
    DECF,
    DECFSZ,
    INCF,
    INCFSZ,
    IORWF,
    MOVF,
    MOVWF,
    NOP,
    RLF,
    RRF,
    SUBWF,
    SWAPF,
    XORWF,

    // BIT-ORIENTED FILE REGISTER OPERATIONS
    BCF,
    BSF,
    BTFSC,
    BTFSS,

    // LITERAL AND CONTROL OPERATIONS
    ADDLW,
    ANDLW,
    CALL,
    CLRWDT,
    GOTO,
    IORLW,
    MOVLW,
    RETFIE,
    RETLW,
    RETURN,
    SLEEP,
    SUBLW,
    XORLW
}
