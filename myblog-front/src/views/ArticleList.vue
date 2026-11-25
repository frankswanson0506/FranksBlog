<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getData } from '../api/http.js'

const router = useRouter()
const articles = ref([])
const error = ref('')

onMounted(async () => {
  try {
    const data = await getData('/articles', {
      params: { page: 1, size: 10 }
    })
    articles.value = data.items
  } catch (e) {
    error.value = e.message
  }
})

function openDetail(id) {
  router.push(`/detail/${id}`)
}
</script>

<template>
  <h1>我的博客</h1>
  <p v-if="error" class="err">{{ error }}</p>
  <ul v-else-if="articles.length">
    <li v-for="a in articles" :key="a.id" @click="openDetail(a.id)">
      <strong>{{ a.title }}</strong>
      <small>{{ a.createdAt }}</small>
    </li>
  </ul>
  <p v-else>这里还没有文章，去后台写第一篇吧 ✍️</p>
</template>

<style scoped>
li { cursor: pointer; padding: 10px; border-bottom: 1px solid #eee; list-style: none; }
.err { color: #c0392b; }
</style>