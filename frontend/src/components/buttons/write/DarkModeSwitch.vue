<template>

	  <v-switch class="mt-5 ml-4"
		v-model="switch1"
		color="#00d5aa"
		dense
		inset
	  />


</template>

<script lang="ts">
	import { Component, Vue, Watch } from 'vue-property-decorator';
	import { eventBus } from '@/main'

	@Component
	export default class DarkModeSwitch extends Vue {
		switch1 = false

		@Watch('switch1')
		watchSwitch1():void{
			//console.log(this.switch1)
			if(this.switch1 == true){
				setTimeout(()=>{
					this.$vuetify.theme.dark = true
				},10)
				
				eventBus.$emit('toDark',true)
			}else{
				setTimeout(()=>{
					this.$vuetify.theme.dark = false
				},10)
				
				eventBus.$emit('toLight',false)
			}
		}

	}
</script>

<style>

</style>