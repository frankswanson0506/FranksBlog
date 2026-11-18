import { createRouter, createWebHistory } from 'vue-router'
import ArticleList from '../views/ArticleList.vue'
import ArticleDetail from '../views/ArticleDetail.vue'

const routes = [
    { path: '/', component: ArticleList },
    { path: '/detail/:id', component: ArticleDetail },
]

export default createRouter({
    history: createWebHistory(),
    routes,
})