import SwiftUI
import SDKFramework

struct ContentView: View {
    let TAG = "ContentView"
    let greet = Greeting().greeting()
    @State private var name = "default name"
    @State private var loadName = "default name"
    
    var body: some View {
        Text(greet)
        TextField("你的名字", text: $name)
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 20)
                    .stroke(Color.blue, lineWidth: 5)
            )
            .padding()
        Button("保存"){
            BusinessKt.setUser(username: name)
            ALogKt.i(tag: TAG, message: name)
        }
        Text(loadName)
        Button("读取"){
            loadName = BusinessKt.getUser()
            ALogKt.i(tag: TAG, message: name)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
