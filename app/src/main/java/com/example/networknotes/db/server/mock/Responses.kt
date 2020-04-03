package com.example.networknotes.db.server.mock

const val RESPONSE_NOTES_LIST = """
    [
          { 
            "id": "1", 
            "title": "Books to read" 
          }, 
          { 
            "id": "2", 
            "title": "Mega Idea"
          }
        ]
"""

const val RESPONSE_GET_NOTE_CONTENT = """
        {
            "id": "2",
            "title": "Mega Idea",
            "content": "Design a room in the Skyrim style"
        }
"""

const val RESPONSE_ADD_NOTE = """
    {
        "id": "2"
    }
"""