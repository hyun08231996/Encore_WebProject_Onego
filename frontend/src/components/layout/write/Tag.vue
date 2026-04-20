<template>
  <div style="margin-top:0px;">
	<div style="width:85%;float:left;">
	<v-card class="pt-1 tag-card" height="7.5vh" elevation="2">
	  <!-- 태그 입력 -->
	  <div v-if="toggleSignTag" style="margin-top:-8px;margin-left:-2px;float:left;" class="v-combobox-tag" id="toggle-sign-tag">
		<v-tooltip right>
	      <template v-slot:activator="{ on, attrs }">
			<v-btn @click="showTag" icon v-bind="attrs" v-on="on"><v-icon small>mdi-chevron-right</v-icon></v-btn>
		  </template>
		  <span>태그</span>
		</v-tooltip>
	  </div>
	  <div v-else style="width:100%;float:left;" class="v-combobox-tag">
		<div style="float:left;margin-top:-8px;margin-left:-2px;"><v-btn @click="showTag" icon><v-icon small>mdi-chevron-down</v-icon></v-btn></div>
		  <div style="float:left; width:88%; padding-top:4px;padding-right:4px;">
			<v-combobox
			:search-input.sync="search"
			v-model="tagList"
			height="5.8vh"
			color="#00d5aa"
			multiple
			outlined
			small-chips
			deletable-chips
			single-line
			dense
			label="태그 입력 후 Enter를 누르세요"
			placeholder="태그 입력 후 Enter를 누르세요"
			@input.native="word=$event.srcElement.value"
			>
			</v-combobox></div>
		  <div style="float:left;width:3%;">
			  <v-tooltip top v-if="showBtn">
				<template v-slot:activator="{ on, attrs }">
					<v-btn icon height="6.5vh" v-bind="attrs" v-on="on" @click="generateTag"><v-icon>mdi-tag-plus-outline</v-icon></v-btn>
				</template>
				<span>태그 자동생성</span>
		      </v-tooltip>
			  <v-progress-circular v-if="isLoading"
			    class="mt-4 ml-2"
				indeterminate
				width="2"
				size="20"
				color="#00d5aa"/>
		  </div>
	  </div>

	  <!-- 이미지 추가 -->
	  <div v-if="toggleSignImg" style="margin-top:-8px;margin-right:-2px;float:right;" class="v-combobox-tag" id="toggle-sign-img">
		<v-tooltip left>
		  <template v-slot:activator="{ on, attrs }">
			<v-btn @click="showFile" icon v-bind="attrs" v-on="on"><v-icon small>mdi-chevron-left</v-icon></v-btn>
		  </template>
		  <span>이미지 추가</span>
		</v-tooltip>
	  </div>
	  <div v-else style="width:100%;float:right;" class="v-combobox-tag">
	    <div style="float:right;margin-top:-8px;margin-right:-2px;"><v-btn @click="showFile" icon><v-icon small>mdi-chevron-down</v-icon></v-btn></div>
		  <div style="float:right; width:93%; padding-top:4px;">
			<v-file-input
			v-model="titleImage"
			type="file"
			accept="image/*"
			height="5.8vh"
			color="#00d5aa"
			dense
			small-chips
			show-size
			outlined
			truncate-length="60"
			prepend-icon="mdi-image"
			placeholder="배경 이미지 파일을 선택하세요"
			@change = "selectImage"
			/>
		  </div>
	  </div>

	</v-card>
	</div>
	<div style="width:15%;float:left;">
	  <v-card height="7.5vh" elevation="2" class="wordcount-card">
		<!-- 글자수 -->
		<div style="float:right;padding-right:8px;padding-top:4px;">
			<label style="opacity:70%;font-size:0.9em;">글자수: {{wordCount}}자</label>
		</div>
	  </v-card>
	</div>
  </div>
</template>

<script lang="ts">
	import { Component, Vue, Watch } from 'vue-property-decorator';
	import { namespace } from 'vuex-class';
	import http from '@/http/http-common'

	const WriteStoreModule = namespace('writeStore')

	@Component
	export default class Tag extends Vue {
		toggleSignTag = true
		toggleSignImg = true
		search = null
		word = null
		tagList : string[] = []
		titleImage: File[] = []
		//image = ''
		content = ''
		showBtn = true
		isLoading = false

		@WriteStoreModule.Getter('getTitleImage')
		private getTitleImage!:File

		@WriteStoreModule.Mutation('setTitleImage')
		private setTitleImage!:(image:File)=>void

		@WriteStoreModule.Mutation('setImageUrl')
		private setImageUrl!:(url:string)=>void

		@WriteStoreModule.Getter('getTagList')
		private getTagList!:string[]

		@WriteStoreModule.Mutation('setTagList')
		private setTagList!:(list:string[])=>void

		@WriteStoreModule.Getter('getWordCount')
		private wordCount!:number

		@WriteStoreModule.Getter('getItemList')
		private itemList!:any[]

		// @WriteStoreModule.Mutation('update')
		// private update!:()=>void

		@Watch('getTagList')
		getTagListWatch():void{
			this.tagList = this.getTagList
		}

		@Watch('tagList')
		tagListWatch():void{
			this.setTagList(this.tagList)
		}

		mounted(){
			this.tagList = this.getTagList
			//console.log(this.getTitleImage.name)
			if(this.getTitleImage.name !== ""){
				this.titleImage.push(this.getTitleImage)
			}

		}

		// updated(){
		// 	this.update()
		// }

		showTag():void{
			this.toggleSignTag = !this.toggleSignTag
			if(this.toggleSignTag == false){
				document.getElementById('toggle-sign-img')!.style.display = "none"
			}else{
				document.getElementById('toggle-sign-img')!.style.display = "block"
			}
		}

		showFile():void{
			this.toggleSignImg = !this.toggleSignImg
			if(this.toggleSignImg == false){
				document.getElementById('toggle-sign-tag')!.style.display = "none"
			}else{
				document.getElementById('toggle-sign-tag')!.style.display = "block"
			}
		}

		selectImage(file:File):void{
			//
			//console.log(typeof(file))
			//console.log(file)
			//this.image = file

			if(file!==null){
				this.setTitleImage(file)
				const url = URL.createObjectURL(file)
				this.setImageUrl(url)
			}else this.setImageUrl('')

		}

		generateTag():void{
			this.isLoading = true
			this.showBtn = false

			var flag = false
			var tags = [] as string[]
			var contentList = [] as string[]
			var contents = [] as string[]
			contentList.push(this.itemList[0].text.replaceAll("\n"," ").replaceAll("</p><p>"," ")
											.replaceAll("<strong>","").replaceAll("</strong>","")
											.replaceAll("<em>","").replaceAll("</em>","")
											.replaceAll("<u>","").replaceAll("</u>",""))
			if(this.itemList[0].children.length !== 0){
				for(var i=0; i<this.itemList[0].children.length;i++){
					contentList.push(this.itemList[0].children[i].text.replaceAll("\n"," ").replaceAll("</p><p>"," ")
														.replaceAll("<strong>","").replaceAll("</strong>","")
														.replaceAll("<em>","").replaceAll("</em>","")
														.replaceAll("<u>","").replaceAll("</u>",""))
				}
			}
			contents.push(contentList.join(" "))
			//console.log(contents)

			http.
				post('/ai/tagger',
					{"contents":contents}
				)
				.then(response=>{
					if (response.status >=200 && response.status < 204){
						//console.log(response.data)
						console.log("success")
						tags = response.data
						for(var j=0;j<tags.length;j++){
							for(var k=0;k<this.tagList.length;k++){
								if(this.tagList[k] === tags[j]) flag = true
							}
							if(flag === false)
								this.tagList.push(tags[j])
						}
						this.isLoading = false
						this.showBtn = true
					} else{
						//console.log(response)
						console.log("fail..")
					}
				})

			
		}

	}
</script>

<style>
.v-combobox-tag .item-title .v-text-field.v-text-field--enclosed:not(.v-text-field--rounded)>.v-input__control>.v-input__slot, .v-text-field.v-text-field--enclosed .v-text-field__details {
    display: none;
}
.v-combobox-tag .v-input__icon{
	height:3.5vh;
	margin-right: 12px !important;
}
.tag-card.v-sheet.v-card {
    border-radius: 0px 0px 0px 4px;
	border-right:none;
}
.wordcount-card.v-sheet.v-card {
    border-radius: 0px 0px 4px 0px;
	border-left:none;
}
.v-combobox-tag .v-text-field--outlined.v-input--dense .v-label {
    top: auto;
}
</style>