package com.example.teachproject

class MyModel {
    companion object Factory {
        fun create() : MyModel = MyModel()        
    }
    
    public fun refreshData(): List<String> {
        return listOf<String>("1", "sdfsdf", "test", "hi")
    }
}
