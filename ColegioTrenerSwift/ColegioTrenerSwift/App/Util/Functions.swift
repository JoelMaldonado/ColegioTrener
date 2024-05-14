//
//  Functions.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI


extension Date {
    func format(pattern: String = "dd/MM/yyyy") -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = pattern
        return formatter.string(from: self)
    }
}

extension String {
    
    func toData<T: Decodable>( _ show: Bool = false) -> EResult<T> {
        if show {
            let a = self
            print("Asi llega A = \(a)")
        }
        guard let jsonData = self.data(using: .utf8) else { return EResult.failure("Error al convertir") }
        do {
            let object = try JSONDecoder().decode(T.self, from: jsonData)
            return EResult.success(object)
        } catch {
            return EResult.failure(error.localizedDescription)
        }
        
    }
    
    func toDate() -> Date? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        dateFormatter.timeZone = TimeZone(identifier: "America/Lima")
        return dateFormatter.date(from: self)
    }
    
    func trim() -> String {
        self.trimmingCharacters(in: .whitespacesAndNewlines)
    }
}

extension Double {
    
    func toSoles() -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.currencyCode = "PEN"
        formatter.locale = Locale(identifier: "es_PE")
        return formatter.string(from: NSNumber(value: self)) ?? ""
    }
    
}
