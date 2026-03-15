import Image from "@/types/image";
import type ImageAPI from "../imageAPI";
import { ref } from "vue";

export default class ImageImpl implements ImageAPI
{
  getImages(page: number): Image[] {
    const images = ref([] as Image[]);

    try
    {
      // build URL with the page search parameter
      const url = new URL('http://localhost:8080/images');
      url.search = `?page=${page}`;

      fetch(url, {
        method: 'GET',
        credentials: 'include'
      })
        .then((response) => response.json())
        .then((json) => {
          json.forEach((element: { path: string; width: number; height: number; }) => {
            images.value.push(new Image(element.path, element.width, element.height));
          });
        });
    }
    catch(err)
    {
      if(err instanceof Error) 
      {
        console.log(err.message);
      }
    }

    return images.value as Image[];
  }
}