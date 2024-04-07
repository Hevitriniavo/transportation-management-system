import { NextRequest, NextResponse } from 'next/server';

export async function middleware(request: NextRequest) {

  const path = request.nextUrl.pathname;

  const isPublicPath = path === "/register" || path === "/login"

  const token =  request.cookies.get("token")?.value || "";

  if(isPublicPath && token){
    return NextResponse.redirect(new URL('/', request.url));
  }

  if(!isPublicPath && !token){
    return NextResponse.redirect(new URL('/login', request.url));
  }
  return NextResponse.next();
}

export const config = {
  matcher: [
    '/register',
    '/login',
    '/logout'
  ],
};
