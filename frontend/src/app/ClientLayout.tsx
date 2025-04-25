'use client'

import Link from 'next/link'
import { useEffect } from 'react'
import { LoginMemberContext, useLoginMember } from '@/stores/auth/loginMember'
import Footer from '@/components/layout/Footer'
import Header from '@/components/layout/Header'

export function ClientLayout({ children }: { children: React.ReactNode }) {
    const { loginMember, setLoginMember, setNoLoginMember, isLoginMemberPending, isLogin, logout, logoutAndHome } =
        useLoginMember()

    // 전역관리를 위한 Store 등록 - context api 사용
    const loginMemberContextValue = {
        loginMember,
        setLoginMember,
        isLoginMemberPending,
        isLogin,
        logout,
        logoutAndHome,
    }

    useEffect(() => {
        fetch('http://localhost:8090/api/v1/members/me', {
            credentials: 'include',
        })
            .then((response) => response.json())
            .then((data) => {
                setLoginMember(data)
            })
            .catch((error) => {
                setNoLoginMember()
            })
    }, [])

    if (isLoginMemberPending) {
        return (
            <div className="flex justify-center items-center h-screen">
                <div className="text-2xl font-bold">로딩중...</div>
            </div>
        )
    }

    return (
        <LoginMemberContext value={loginMemberContextValue}>
            <main className="flex flex-col min-h-screen">
                {/* 헤더 영역 */}
                <Header />
                {children}
                {/* 푸터 영역 */}
                <Footer />
            </main>
        </LoginMemberContext>
    )
}
