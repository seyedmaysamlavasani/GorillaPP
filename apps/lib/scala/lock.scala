package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class lock (extCompName:String) (idWidthLog: Int = 8) (waitListLength: Int = 32) extends gComponentLeaf  (() => new Lock_int_t(UFix(width = idWidthLog))) (() => new Lock_out_t) (ArrayBuffer()) (extCompName=extCompName) with include {
  val stat = Mem(Bool(), 1 << idWidthLog) 
  val waitList = Mem(UFix(width = waitListLength), 1 << idWidthLog)
  val waitListOfThisLock = waitList(io.in.bits.id)
  val winnerWaiter = PEncode(waitListOfThisLock)
  val statOfThisLock = state(io.in.bits.id)
  val State = Reg(UFix(width=8), resetVal=Waiting) 
  val Waiting::CheckStat:RemoveWaiter::SendReadyOnInput::SendResponse::Nil = Enum(UFix(5), 3)

  //If command is lock check the lock status
  when (State === Waiting && io.in.valid && io.in.bits.command === LockCommand) {
    State := CheckStat    
  }
  //If lock is free grant the lock 
  when (State === CheckStat && requestedLockStat === Free) {
    stat(io.in.bits.id) := Bool(true) 
    State := Waiting
  } 
  //If lock is grabbed add the thread to wait list
  when (State === CheckStat && requestedLockStat === Grabbed) {
    waitList(io.in.bits.id) := waitLisOfThisLock | (1 << io.in.bits.tag)
    State := Waiting
  } 
  //If command is unlock change (i) the change the stat and (ii) in a loop send acks to waiters   
  when  (State === Waiting && io.in.valid && io.in.bits.command === UnlockCommand) {
    stat(io.in.bits.id) := Bool(false)
    State := RemoveWaiter 
  }  
  when (State === RemoveWaiter && winnerWaiter != NoWaiter) {
    waitList(io.in.bits.id) := waitListOfThisLock & ~(1 << io.in.bits.tag)
  }
  when (State === RemoveWaiter && winnerWaiter === NoWaiter) {
    State := SendReadyOnInput
  }
  when (State === SendReadyOnInput) {
    io.out.ready = Bool(true)
    State := SendResponse
  } .elsewhen {
    io.out.ready = Bool(false)
  }
  when (State === SendResponse) {
    io.out.valid = Bool(true)
    State := WaitForOutputReady
  }
  when (State === WaitForOuptutReady && io.out.ready) {
    State := Waiting
  } 
} 
