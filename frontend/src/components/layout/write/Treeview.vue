<template>
	<div id="treeview-margin">
	  <div id="treeview-size" >
		<div v-if="toggleSignIndex" style="margin-left:8px;">
			<v-tooltip right>
			  <template v-slot:activator="{ on, attrs }">
				<v-btn @click="showTreeview" icon v-bind="attrs" v-on="on"><v-icon>mdi-chevron-right</v-icon></v-btn>
			  </template>
			  <span>목차</span>
			</v-tooltip>
		</div>
		<div v-else style="margin-left:8px;position:relative;">
			<v-btn @click="showTreeview" icon><v-icon>mdi-chevron-down</v-icon></v-btn>
			<span style="position:absolute;right:8px;">
		      <v-tooltip top>
				<template v-slot:activator="{ on, attrs }">
				  <v-btn icon v-bind="attrs" v-on="on" @click="addContent"><v-icon>mdi-folder-plus-outline</v-icon></v-btn>
				</template>
				<span>목차추가</span>
			  </v-tooltip>
			</span>
		</div>
		<div v-show="showTree" style="margin-left:5px;margin-top:10px;">
		  <v-treeview
			v-model="tree"
			color="#00d5aa"
			:items="itemList"
			activatable
			transition
			@update:active="setActiveVal"
			open-all
			dense
			item-key="id"
		  ><!--@update:active="setActiveVal"-->
		    <template v-slot:prepend="{ item, open, active }">
			  <v-icon v-if="item.isContent" :color="active ? '#00d5aa' : ''">
				mdi-folder-outline
			  </v-icon>
			  <v-icon v-else :color="active ? '#00d5aa' : ''">
				{{ open ? 'mdi-folder-open' : 'mdi-folder' }}
			  </v-icon>
			</template>
		    <template v-slot:label="{ item, active }" >
			  <div>
			  <a class="itemLink" @click="sendContent(item.id)" @contextmenu="showMenu($event,item.id)" style="width:100%;">
				<div class="item-title" @click="active ? $event.stopPropagation() : null" style="width:100%;">
				  <v-text-field v-model="item.title"  flat single-line solo hide-details="true" background-color="transparent" readonly /><!--@keyup="setTitle(item.id,item.title)"-->
				</div>
			  </a>
			  <v-menu content-class="elevation-0"
			    class="tree-menu"
				v-model="isMenu"
				:position-x="x"
				:position-y="y"
				absolute
				offset-y
			  >
			    <v-list outlined rounded dense>
					<a @click="editTitle()" id="menu-edit-hover" >
					  <div id="list-item-edit">
					    <v-list-item style="margin-top:-8px;padding-right:50px;padding-left:5px;" >
						  <v-list-item-title><label id="edit-label" style="font-size:0.95em;opacity:70%;">수정</label></v-list-item-title>
					    </v-list-item>
					  </div>
					</a>
					<div v-if="isDeletable">
						<v-divider />
						<a @click="deleteContent()" id="menu-delete-hover">
						<div id="list-item-delete">
						  <v-list-item style="margin-bottom:-8px;padding-right:50px;padding-left:5px;" >
							<v-list-item-title><label id="delete-label" style="font-size:0.95em;opacity:70%;">삭제</label></v-list-item-title>
						  </v-list-item>
						</div>
						</a>
					</div>
				</v-list>
			  </v-menu>
			  </div>
			</template>

		  </v-treeview>
		</div>
	  </div>
	</div>
</template>

<script lang="ts">
	import { Component, Vue, Prop } from 'vue-property-decorator';
	import { namespace } from 'vuex-class';
	import { eventBus } from '@/main'

	const WriteStoreModule = namespace('writeStore')

	@Component
	export default class Treeview extends Vue {
		@Prop({ type: String }) readonly tempBoardId!: string
		@Prop({ type: String }) readonly boardId!: string

		tree = []
		showTree = true
		toggleSignIndex = false
		isMenu = false
		isDeletable = true
		curId = 0
		x = 0
		y = 0

		@WriteStoreModule.Mutation('sendTreeTitle')
		private sendTreeTitle!:(title:string)=>void

		@WriteStoreModule.Getter('getActiveVal')
		private activeVal!:number

		@WriteStoreModule.Getter('getContent')
		private getContent!:string

		@WriteStoreModule.Mutation('setActiveVal')
		private setActiveVal!:(val:any)=>void

		@WriteStoreModule.Mutation('sendContent')
		private sendContent!:(itemId:number)=>void

		@WriteStoreModule.Getter('getItemList')
		private itemList!:any[]

		mounted () {
			eventBus.$on("clickFirstTree",this.clickFirstTree)
			eventBus.$on("clickFirstTreePost",this.clickFirstTree)
			//console.log(this.tempBoardId)
			if(this.tempBoardId===undefined || this.boardId === undefined){
				let element: HTMLElement = document.getElementsByClassName('v-treeview-node__label')[0].children[0].children[0] as HTMLElement
				element.click()
			}
		}

		clickFirstTree():void{
			//console.log(this.itemList[0].title)
			let element: HTMLElement = document.getElementsByClassName('v-treeview-node__label')[0].children[0].children[0] as HTMLElement
			element.click()
		}

		showTreeview():void{
			this.showTree = !this.showTree
			this.toggleSignIndex = !this.toggleSignIndex
		}

		addContent():void{
			if(this.itemList[0].children.length === 0){
				this.itemList[0].children.push({id:this.itemList[0].id+1,title:'',subtitle:'',isContent:true,text:''})
			}else{
				this.itemList[0].children.push({id:this.itemList[0].children[this.itemList[0].children.length-1].id+1,title:'',subtitle:'',isContent:true,text:''})
			}

		}

		editTitle():void{
			const itemId = this.curId
			//console.log(itemId)
			const inputDiv = document.getElementsByClassName('v-text-field__slot')
			for(var i=0; i<=inputDiv.length; i++){
				if(i+1 === itemId){
					const inputId = inputDiv[i].children[0].id
					const element = document.getElementById(inputId) as HTMLInputElement
					element.readOnly = false
					//document.getElementById(inputId)?.focus()
					element.focus()
					element.setSelectionRange(0,element.value.length)

					element.addEventListener('keypress', (e)=>{
						if(e.key === 'Enter') {
							if(this.activeVal !== itemId ){
								let node: HTMLElement = document.getElementsByClassName('v-treeview-node__label')[itemId-1].children[0].children[0] as HTMLElement
								node.click()
							}else{
								const inputId = document.getElementsByClassName('v-text-field__slot')[itemId-1].children[0].id
								const element = document.getElementById(inputId) as HTMLInputElement
								const title = element.value
								//console.log(typeof(title))
								this.sendTreeTitle(title)
							}
							element.readOnly = true
						}
					})
				}
			}
		}

		deleteContent():void{
			const itemId = this.curId
			const items = this.itemList[0].children
			const index = this.indexWhere(items, (item: { id: number; }) => item.id === itemId)
			//console.log(index)
			this.itemList[0].children.splice(index,1)
			let node: HTMLElement = document.getElementsByClassName('v-treeview-node__label')[index].children[0].children[0] as HTMLElement
			node.click()
		}

		indexWhere(array: any[], conditionFn: (item: { id: number; }) => boolean):number{
			const item = array.find(conditionFn)
  			return array.indexOf(item)
		}

		showMenu(event:any,itemId:number):void{
			//console.log(itemId)
			this.curId = itemId
			if(itemId==1){
				this.isDeletable = false
			}else this.isDeletable = true
			event.preventDefault()
			this.isMenu = false
			this.x = event.clientX
			this.y = event.clientY
			this.$nextTick(() => {
				this.isMenu = true
			})
		}

	}
</script>

<style>
#menu-edit-hover:hover #list-item-edit,
#menu-delete-hover:hover #list-item-delete{
	background-color:#89e7d427 !important;
}
#edit-label:hover,
#delete-label:hover{
	cursor: pointer !important;
}
#menu-edit-hover:hover #edit-label,
#menu-delete-hover:hover #delete-label{
	color:#00d5aa;
	font-weight:900;
	opacity:100%;
}
</style>