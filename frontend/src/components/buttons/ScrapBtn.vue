<template>
    <v-btn v-if="this.scraps==false" rounded outlined color="#00d5aa" class="ma-2 px-6" id="post-scrap-btn" 
    @click="getlike()">
        스크랩
    </v-btn>
    <v-btn v-else rounded outlined color="#00d5aa" value="" class="ma-2" id="post-unscrap-btn" @click="getunlike()"></v-btn>
</template>


<script lang="ts">
import Vue from 'vue'
import http from '@/http/http-common'

export default Vue.extend({
    name:"ScrapBtn",
    data: ()=>({
        errored: false,
        loading: true,
        scraps:false,
        loginUser:'',
    }),
    methods:{
        async getlike(){
            var userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
            await http
                .post('/'+this.$route.params.boardId, {"userEmail":this.$store.state.user.userAccount.attributes.email},{
                      headers:{'Authorization': 'Bearer '+localStorage.getItem('accessToken')
                }})
                .then(response => {
                  // this.getArticle(this.$route.params.boardId)
                  userInfo.likes.push(this.$route.params.boardId)
                  localStorage.setItem('userInfo', JSON.stringify(userInfo))
                  this.scraps = true
                })
                .catch(() => this.errored = true )
                .finally(() => {
                  this.loading = false
                })
        },
        async getunlike(){
            var userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
            await http
                .post('/'+this.$route.params.boardId, {"userEmail":this.$store.state.user.userAccount.attributes.email},{
                      headers:{'Authorization': 'Bearer '+localStorage.getItem('accessToken')
                }})
                .then(response => {
                  // this.getArticle(this.$route.params.boardId)
                  userInfo.likes = userInfo.likes.filter((element: any) => element !== this.$route.params.boardId)
                  localStorage.setItem('userInfo', JSON.stringify(userInfo))
                  this.scraps = false
                })
                .catch(() => this.errored = true )
                .finally(() => {
                  this.loading = false
                })
        },
        async getArticle(boardId: string){
          var userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
          await http
              .get('/board', {
                params: { 'boardId': boardId }, 
                })
              .then(response => {
                  if(this.loginUser != undefined){
                    for(var i=0; i<userInfo.likes.length; i++){
                        if(boardId === userInfo.likes[i]){
                            this.scraps = true
                            break
                        }
                    }
                  }
              })
        }
    },
    created(){  
      var userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
      this.loginUser = userInfo.email
      this.getArticle(this.$route.params.boardId)
    }

})
</script>>

<style>
  #post-unscrap-btn{
	background-color: #00d5aa !important;
    color: white !important;
  }
  #post-unscrap-btn::after{
    content: "스크랩 ✓";
  }
  #post-unscrap-btn:hover{
    color: #00d5aa !important;
    border-color: #00d5aa !important;
    background-color: white !important;
  }
  #post-unscrap-btn:hover:after{
    content: "스크랩취소";
    padding-left:-12px;
    padding-right:-12px;
  }
</style>

