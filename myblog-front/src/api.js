const BASE = 'http://localhost:8080'

export async function getJson(url) {
    const res = await fetch(BASE + url)
    if (!res.ok) throw new Error('HTTP ' + res.status)
    const body = await res.json()
    if (body.code !== 0) {
        throw new Error(body.message || '业务错误')
    }
    return body
}