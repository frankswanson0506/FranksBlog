import axios from 'axios'

// 共享客户端：baseURL 只写一次
export const http = axios.create({
    baseURL: 'http://localhost:8080'
})

// Result 辅助函数：统一检查 code、拆包 data
export async function getData(url, config) {
    const { data: body } = await http.get(url, config)
    if (body.code !== 0) {
        throw new Error(body.message || '请求失败')
    }
    return body.data
}