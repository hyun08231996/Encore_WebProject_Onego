<template>
    <v-btn rounded outlined color="#00d5aa" class="ma-2 px-8" id="scrap-btn" 
    @click="postput()">
        수정
    </v-btn>
</template>


<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
    props:{
        boardId:String
    },
    name:"PostUpdateBtn",
    data: ()=>({
    }),
    methods:{
        postput(){
          if(!confirm("글 수정 페이지로 이동하시겠습니까?")) return
          else window.open('/write/post/'+this.boardId,'_self')
        }
    },

})
</script>>

<style scoped>



</style>

