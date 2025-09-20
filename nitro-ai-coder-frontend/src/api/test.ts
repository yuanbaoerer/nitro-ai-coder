// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** 此处后端没有提供注释 GET /test/hello */
export async function helloCheck(options?: { [key: string]: any }) {
  return request<API.BaseResponseString>('/test/hello', {
    method: 'GET',
    ...(options || {}),
  })
}
