export const API_URL = import.meta.env.VITE_API_URL;

export async function getJson(url: string) {
  try {
    return await (await fetch(url, {
      credentials: 'include'
    })).json()
  }
  catch(e) { console.log(e) }
}