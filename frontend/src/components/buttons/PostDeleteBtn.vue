<template>
    <v-btn rounded outlined color="#00d5aa" class="ma-2 px-8" id="scrap-btn" 
    @click="postdelete()">
        삭제
    </v-btn>
</template>


<script lang="ts">
import Vue from 'vue'
import http from '@/http/http-common'

export default Vue.extend({
    props:{
        boardId:String
    },
    name:"PostDeleteBtn",
    data: ()=>({
    }),
    methods:{
        postdelete(){
          var con_test = confirm("글을 삭제하시겠습니까?");
          if(con_test == true){
              http
                .delete('/board', {
                data: {'boardId': this.boardId}, 
                headers:{
                        'Authorization': 'Bearer '+localStorage.getItem('accessToken')
                    }})
                .then(response => {
                     location.href = '/article'
                })
          }
          else if(con_test == false){
              return
          }
          
        }
    },
    

})
</script>>

<style scoped>



</style>

