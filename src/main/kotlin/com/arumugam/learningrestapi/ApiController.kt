package com.arumugam.learningrestapi

import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/api")
class ApiController{

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id",required=true) id : String):String {
        var file = File("files/"+id+".txt")
        var fileExist : Boolean = file.exists()

        if(fileExist){
            var body : String = file.readText()
            return "<h1>File contents are:</h1>"+body
        }

        return "File doesn't exist"
    }

    @PostMapping("/{id}")
    fun post(@PathVariable(value = "id",required = true) id : String , @RequestBody(required = true) body : String): String {
        var file = File("files/" + id + ".txt")

        var fileCreated: Boolean = file.createNewFile()

        if (fileCreated) {
            file.writeText(body);
            return "file created at " + file.absolutePath
        }

        return "File already exist.!"
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id : String) : String{
        var file : File = File("files/"+id+".txt")
        var fileExists : Boolean = file.exists()
        if(fileExists){
            var fileDeleted : Boolean = file.delete()
            if(fileDeleted)
                return "file exist and deleted successfully.!"
            return "file exist but deletion did not happen successfully.!"
        }
        return "file does not exist"
    }
}