import Image from "@/types/image";

export default interface ImageAPI
{
  getImages(page: number): Image[];
}