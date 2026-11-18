<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getJson } from '../api.js'

const route = useRoute()
const router = useRouter()
const article = ref(null)
const error = ref('')

onMounted(async () => {
  try {
    const body = await getJson(`/articles/${route.params.id}`)
    article.value = body.data
  } catch (e) {
    error.value = e.message
  }
})

function goBack() {
  router.push('/')
}
</script>

<template>
  <p v-if="error" class="err">{{ error }}</p>
  <div v-else-if="article">
    <h2>{{ article.title }}</h2>
    <p>{{ article.content }}</p>
    <button @click="goBack">返回列表</button>
  </div>
</template>

<style scoped>
.err { color: #c0392b; }
button { margin-top: 16px; padding: 6px 14px; cursor: pointer; }
</style>