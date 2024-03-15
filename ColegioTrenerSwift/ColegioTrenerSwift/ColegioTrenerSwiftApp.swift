//
//  ColegioTrenerSwiftApp.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

@main
struct ColegioTrenerSwiftApp: App {
    @State private var isSplashActive: Bool = true
    var body: some Scene {
        WindowGroup {
            ZStack {
                NavigationStack {
                    LoginView()
                }

                SplashView(isActive: $isSplashActive)
            }
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                    withAnimation {
                        self.isSplashActive = false
                    }
                }
            }
        }
    }
}

struct SplashView: View {
    @Binding var isActive: Bool

    var body: some View {
        ZStack {
            
            Color(.colorP1)
                .edgesIgnoringSafeArea(.all)

            Image(.logo)
                .resizable()
                .scaledToFit()
                .frame(width: 300)
        }
        .opacity(isActive ? 1 : 0)
    }
}

struct ContentView2: View {
    @State var presentSideMenu = false
    
    var body: some View {
        
        ZStack {
            Color.white.edgesIgnoringSafeArea(.all)
            
            VStack {
                Text("Hola mundo")
                Text("Hola mundo")
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .overlay(
                ZStack {
                    HStack {
                        Button {
                            presentSideMenu.toggle()
                        } label: {
                            Image(systemName: "person")
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        }
                        .frame(width: 24, height: 24)
                        .padding(.leading, 30)
                        
                        Spacer()
                    }
                }
                    .frame(maxWidth: .infinity)
                    .frame(height: 56)
                    .background(Color.white)
                    .zIndex(1)
                    .shadow(radius: 0.3)
                , alignment: .top
            )
            
            SideMenu()
        }
        
        .frame(maxWidth: .infinity)
        
    }
    
    
    @ViewBuilder
    private func SideMenu() -> some View {
        SideView(isShowing: $presentSideMenu, direction: .leading) {
            SideMenuViewContents(presentSideMenu: $presentSideMenu)
                .frame(width: 300)
        }
    }
    
}

struct SideMenuViewContents: View {
    @Binding var presentSideMenu: Bool
    
    var body: some View {
        ZStack {
            VStack(alignment: .leading, spacing: 0) {
                SideMenuTopView()
                VStack {
                    Text("Side Menu")
                        .foregroundColor(.white)
                }.frame( maxWidth: .infinity, maxHeight: .infinity)
            }
            .frame(maxWidth: .infinity)
            .background(.gray)
        }
    }
    
    func SideMenuTopView() -> some View {
        VStack {
            HStack {
                Button(action: {
                    presentSideMenu.toggle()
                }, label: {
                    Image(systemName: "x.circle")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .foregroundColor(.white)
                })
                .frame(width: 34, height: 34)
                Spacer()
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.leading, 40)
        .padding(.top, 40)
        .padding(.bottom, 30)
    }
}

struct SideView<RenderView: View>: View {
    @Binding var isShowing: Bool
    var direction: Edge
    @ViewBuilder  var content: RenderView
    
    var body: some View {
        ZStack(alignment: .leading) {
            if isShowing {
                Color.black
                    .opacity(0.3)
                    .ignoresSafeArea()
                    .onTapGesture {
                        isShowing.toggle()
                    }
                content
                    .transition(.move(edge: direction))
                    .background(
                        Color.white
                    )
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
        .ignoresSafeArea()
        .animation(.easeInOut, value: isShowing)
    }
}
