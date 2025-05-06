package com.example.models

case class Command(commandId : String):
    def Id() = println(commandId)